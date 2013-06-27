/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.echo.bean;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;


/**
 *
 * @author New User
 */
public class SongMetadata {

    public String getEncoder() {
        return encoder;
    }

    
    
   
    MP3File mP3File;
    MP3AudioHeader mp3header;
    String fileName = "";

    long noOfFrames = 0;
    String absolutePath = "";
    String channels = "";
    String bitRate = "";
    String sampleRate = "";
    String trackLength = "";
    int trackLengthInteger = 0;
    long filesize = 0;
    long mp3StartByte = 0;
    String encoder = "";
    String title = "";
    String artist = "";
    String genre = "";
    String album = "";
    String year = "";
    
    
    public SongMetadata(String mp3File){
        try {
            File mp3file=new File(mp3File);
            filesize = mp3file.length();
            mP3File = (MP3File)AudioFileIO.read(mp3file);
            Tag songTag = mP3File.getTag();
            if(mP3File.hasID3v1Tag()){
                //JOptionPane.showMessageDialog(null, "ID3 v1 ");
                artist = artist + songTag.getFirst(FieldKey.ARTIST);
                title = title + songTag.getFirst(FieldKey.TITLE);
                
                album = album + songTag.getFirst(FieldKey.ALBUM);
                genre = genre + songTag.getFirst(FieldKey.GENRE);
                year = year + songTag.getFirst(FieldKey.YEAR);
            }
            if(mP3File.hasID3v2Tag()){
                //JOptionPane.showMessageDialog(null, "ID3 v2 ");
                AbstractID3v2Tag id3v2 = mP3File.getID3v2Tag();
                artist =  id3v2.getFirst(FieldKey.ARTIST);
                title =  id3v2.getFirst(FieldKey.TITLE);
                 
                album =  id3v2.getFirst(FieldKey.ALBUM);
                genre =  id3v2.getFirst(FieldKey.GENRE);
                year =  id3v2.getFirst(FieldKey.YEAR);
            }
            //JOptionPane.showMessageDialog(null, "\nArtist:"+artist+"\ntitle:"+title+"\n Album" + album);
             absolutePath=mp3file.getAbsolutePath();

   //            
   //            mp3header = new MP3AudioHeader(mp3file);
   //            channels = mp3header.getChannels();
   //            bitRate = mp3header.getBitRate();
   //            sampleRate = mp3header.getSampleRate();
   //            trackLength = mp3header.getTrackLengthAsString();
               
                mp3header = new MP3AudioHeader(mp3file);
               channels = mp3header.getChannels();
               bitRate = mp3header.getBitRate();
               sampleRate = mp3header.getSampleRate();
               trackLength = mp3header.getTrackLengthAsString();
               trackLengthInteger = mp3header.getTrackLength();
               encoder = mp3header.getEncoder();
               noOfFrames = mp3header.getNumberOfFrames();
               mp3StartByte = mp3header.getMp3StartByte();
               fileName = mp3file.getName();

            
        } catch (CannotReadException ex) {
            Logger.getLogger(SongMetadata.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SongMetadata.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TagException ex) {
            Logger.getLogger(SongMetadata.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ReadOnlyFileException ex) {
            Logger.getLogger(SongMetadata.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAudioFrameException ex) {
            Logger.getLogger(SongMetadata.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    public long getNoOfFrames() {
        return noOfFrames;
    }

    public long getMp3StartByte() {
        return mp3StartByte;
    }
    
    

    public long getFilesize() {
        return filesize;
    }
    
    public int getTrackLengthInteger() {
        return trackLengthInteger;
    }
    
    public String getFileName() {
        return fileName;
    }
    public String getAbsolutePath() {
        return absolutePath;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public String getBitRate() {
        return bitRate;
    }

    public String getChannels() {
        return channels;
    }

    public String getGenre() {
        return genre;
    }

    

    public String getSampleRate() {
        return sampleRate;
    }

    public String getTitle() {
        return title;
    }

    public String getTrackLength() {
        return trackLength;
    }

    public String getYear() {
        return year;
    }
    
}
