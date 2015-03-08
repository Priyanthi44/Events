package com.code.qr.events.process;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Environment;

import com.code.qr.events.Fields.Directories;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Folders {

    public static boolean getDrive(){
        boolean mExternalStorageAvailable = false;
        boolean mExternalStorageWriteable = false;
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media
            mExternalStorageAvailable = mExternalStorageWriteable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // We can only read the media
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = false;
        } else {
            // Something else is wrong. It may be one of many other states, but all we need
            //  to know is we can neither read nor write
            mExternalStorageAvailable = mExternalStorageWriteable = false;
        }
        return mExternalStorageWriteable&mExternalStorageAvailable;
    }
	public static ArrayList<String[]> list;
	public static  String dir;


	
	public static void createDirectory(Context c){
		if(getDrive()){
            dir =Environment.getExternalStorageDirectory().getAbsolutePath();
            dir+= Directories.folder;
            //create a File object for the parent directory

        }else{
            ContextWrapper cw = new ContextWrapper(c.getApplicationContext());
            // path to /data/data/yourapp/app_data/imageDir
           dir= cw.getDir(Directories.folder, Context.MODE_PRIVATE).getAbsolutePath();
        }

        File wallpaperDirectory = new File(dir);
        // have the object build the directory structure, if needed.
        wallpaperDirectory.mkdirs();
	}

public static ArrayList createList(String dfolder){
    list = new ArrayList<String[]>();
int i =0;
	File folder = new File(dfolder);
	    for ( File file : folder.listFiles()) {

	        		 list.add(Schedule.readObject(file.getAbsolutePath()));
	        	 }



	return list;
	
}

public Schedule retrieveReminder(){
	Schedule s = null;
	return s;
	
}


}