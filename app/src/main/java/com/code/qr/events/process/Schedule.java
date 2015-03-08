package com.code.qr.events.process;

import com.code.qr.events.Fields.Directories;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Schedule implements Serializable {
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	private String event;
	private String eventdate;
	private String reminder;

	/* Constructor */
	public Schedule(String event, String eventdate, String reminder) {
		setEvent(event);
		setEventdate(eventdate);
		setReminder(reminder);

	}

	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		// write 'this' to 'out'..

		out.writeChars(getEvent());
		out.writeChars(getEventdate());
		out.writeChars(getReminder());
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		// populate the fields of 'this' from the data in 'in'...
	}
	
	public static void saveReminder(Schedule c){
		// create a File object for the output file
		File outputFile = new File(Folders.dir, c.event+"-"+c.eventdate+ Directories.ext);
		// now attach the OutputStream to the file object, instead of a String representation
		try {
            outputFile.createNewFile();
            FileWriter writer = new FileWriter(outputFile);
            // Writes the content to the file
            writer.write(c.toString());
            writer.flush();
            writer.close();
	
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e1) {
		
			e1.printStackTrace();
		}
	}
	
	public String toString() {
		String b = getEvent() + "\n";

		b+= getEventdate().toString() + "\n";
		b+= getReminder().toString();
		return b;

	}
	
	public static String[] readObject(String file){
        String list[] =new String[2];
        try {
        String line =null;

        File f = new File(file);
           int k=0;
    FileReader in = new FileReader(f);
    BufferedReader br = new BufferedReader(in);
    while ((line =br.readLine())!=null){

        if(k==0)
            list[0] =line;
        if (k==1)  {
           list[1]=line;

            br.close();
            in.close();
            break;
        }
        k++;
    }
        }catch(Exception fd){

            }

            return list;
    }

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getEventdate() {
		return eventdate;
	}

	public void setEventdate(String eventdate2) {
		this.eventdate = eventdate2;
	}

	public String getReminder() {
		return reminder;
	}

	public void setReminder(String reminder) {
		this.reminder = reminder;
	}

}
