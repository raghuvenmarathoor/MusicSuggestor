#include <stdio.h>
#include <string.h>
#ifndef _WIN32
	#include <libgen.h>
	#include <dirent.h>
#endif
#include <stdlib.h>
#include <stdexcept>
#include <vector>
#include "Codegen.h"

using namespace std;

/* Simplest possible example of linking libcodegen into an app.
   Here I simply read in a .raw file into a PCM buffer and ask the codegen to print out the codes to stdout.
   Note for 'real code' you will want to add other features: you should extract ID3 tags and add them to the query,
   you should decode other types of files (mp3, aac etc... for example using libavcodec, quicktime, etc)
   
   Compile this by doing

   g++ -o codegen main.cxx -lcodegen.3.1.0
   
   assuming you've moved libcodegen.3.1.0.dylib/.so somewhere ldconfig knows about.    
*/

int main(int argc, char** argv) 
{
    if (argc < 2)
    {
        fprintf(stderr, "Usage: %s filename.raw [offset]\n", argv[0]);
        exit(-1);
    }

	try{
    int offset = 0;
	if(argc>2) {
        offset = atoi(argv[2]);
    }
    char *filename = argv[1];

    // Open the raw file, which I created by doing
    // ffmpeg -i billie_jean.mp3 -ac 1 -ar 22050 -f s16le -t 20 -ss 10 - > billie.raw
    // and read 5s at a time into a buffer

    FILE *pFile = fopen(filename, "rb");    
    std::vector<short*> vChunks;
    uint _NumberSamples = 0;
    uint nSamplesPerChunk = (uint) 22050 *5;
    uint samplesRead = 0;
    do {
        short* pChunk = new short[nSamplesPerChunk];
        samplesRead = fread(pChunk, sizeof (short), nSamplesPerChunk, pFile);
        _NumberSamples += samplesRead;
        vChunks.push_back(pChunk);
    } while (samplesRead > 0);

    // Convert from shorts to 16-bit floats and copy into sample buffer.
    uint sampleCounter = 0;
    float * _pSamples = new float[_NumberSamples];
    uint samplesLeft = _NumberSamples;
    for (uint i = 0; i < vChunks.size(); i++) 
    {
        short* pChunk = vChunks[i];
        uint numSamples = samplesLeft < nSamplesPerChunk ? samplesLeft : nSamplesPerChunk;
        for (uint j = 0; j < numSamples; j++)
            _pSamples[sampleCounter++] = (float) pChunk[j] / 32768.0f;
        samplesLeft -= numSamples;
        delete [] pChunk, vChunks[i] = NULL;
    }
    
    
    /* OK, now do the code generation. Single call, takes pcm buffer (must be floats @ 22050Hz, mono), number of samples, and offset if given
       offset simply hints the FP where in the file you are taking these samples from. in this sample we take it as a CL parameter
       of course, in real code you can choose the offset programatically
    */
    
    Codegen * pCodegen = new Codegen(_pSamples, _NumberSamples, offset);

    // Print the query JSON direct to stdout.
    // NOTE: you should be escaping the filename string, the API may fail if you give it malformed JSON
    printf("{\"metadata\":{\"filename\":\"%s\", \"samples_decoded\":%d, \"version\":%2.2f}, \"code\":\"%s\"}",
        filename,
        sampleCounter,
        pCodegen->getVersion(),
        pCodegen->getCodeString().c_str());
    // NOTE that you can give other fields to metadata that will hint the FP: artist, title, release, genre, bitrate, sample_rate, duration

	return 1;
	}catch(std::runtime_error& ex){
		fprintf(stderr, "%s\n", ex.what());
		return -1;
	}
}
