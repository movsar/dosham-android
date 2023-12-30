package com.nohchiyn.services

import android.content.Context
import com.nohchiyn.R
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.util.zip.ZipFile

class FileService(private val context: Context) {
    fun deployLocalDatabase() {
        val zipfileName = "data.zip";
        val dbFileName = "local.datx";

        val zipFile = File(context.filesDir, zipfileName);
        val dbFile = File(context.filesDir, dbFileName);
        if (dbFile.exists() && zipFile.exists()) {
            return;
        }

        val rawResourceId = R.raw.data;
        copyRawFileToInternalStorage(rawResourceId, zipfileName);
        unzip(zipfileName, "./");
    }

    private fun copyRawFileToInternalStorage(rawResourceId: Int, fileName: String) {
        context.resources.openRawResource(rawResourceId).use { inputStream ->
            context.openFileOutput(fileName, Context.MODE_PRIVATE).use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        }
    }

    fun unzip(zipFileName: String, destinationDirectory: String) {
        try {
            val zipFile = File(context.filesDir.absolutePath + File.separator + zipFileName)
            val destinationDirectoryPath = context.filesDir.absolutePath + File.separator + destinationDirectory

            File(destinationDirectoryPath).run {
                if (!exists()) {
                    mkdirs()
                }
            }

            ZipFile(zipFile).use { zip ->
                zip.entries().asSequence().forEach { entry ->
                    zip.getInputStream(entry).use { input ->
                        val filePath = destinationDirectoryPath + File.separator + entry.name
                        if (!entry.isDirectory) {
                            // if the entry is a file, extracts it
                            extractFile(input, filePath)
                        } else {
                            // if the entry is a directory, make the directory
                            val dir = File(filePath)
                            dir.mkdir()
                        }

                    }

                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * Extracts a zip entry (file entry)
     * @param inputStream
     * @param destFilePath
     * @throws IOException
     */
    @Throws(IOException::class)
    private fun extractFile(inputStream: InputStream, destFilePath: String) {
        val bos = BufferedOutputStream(FileOutputStream(destFilePath))
        val bytesIn = ByteArray(BUFFER_SIZE)
        var read: Int
        while (inputStream.read(bytesIn).also { read = it } != -1) {
            bos.write(bytesIn, 0, read)
        }
        bos.close()
    }

    /**
     * Size of the buffer to read/write data
     */
    private val BUFFER_SIZE = 4096
}
