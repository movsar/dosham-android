package com.nohchiyn.services

import android.content.Context
import com.nohchiyn.R
import okio.Path
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.attribute.PosixFilePermissions
import java.util.zip.ZipFile

class FileService(private val context: Context) {
    fun setPermissions(filePath: String) {
        val path = Paths.get(filePath)
        Files.setPosixFilePermissions(path, PosixFilePermissions.fromString("rwxrwxrwx"))
    }

    val SCHEMA_19_ZIP_LENGTH: Long = 12304333
    fun deployLocalDatabase() {
        val zipfileName = "data.zip";
        val dbFileName = "local.datx";

        val zipFile = File(context.filesDir.absolutePath, zipfileName);
        val zipFileLength = zipFile.length();

        val isZipSizeCorrect = zipFileLength == SCHEMA_19_ZIP_LENGTH;

        val dbFile = File(context.filesDir.absolutePath + Path.Companion.DIRECTORY_SEPARATOR + "database", dbFileName);

        val dbFileExists = dbFile.exists()
        val zipFileExists = zipFile.exists()

        if (dbFileExists && zipFileExists && isZipSizeCorrect) {
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
            val destinationDirectoryPath =
                context.filesDir.absolutePath + File.separator + destinationDirectory

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
