package com.td.exts

import android.util.Log
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream


/**
 * 拷贝文件到应用私有目录
 */
fun File.copyToDir(){

}

/**
 * 拷贝文件到sd下
 */
fun File.copyToSD(){

}

/**
 * 拷贝文件到指定目录
 */
fun File?.copy(destPath : String){
    this?.run {
        // 待拷贝文件确实存在 执行拷贝操作，否则输出日志
        if (this.exists()){

        } else {

        }
    }
}

/**
 * 拷贝文件到指定目录
 */
private fun copyFile(filePath: String, destPath: String, isCover: Boolean = true): Boolean {
    var result: Boolean = false
    val tempFile = File(filePath)
    // 待拷贝文件确实存在 执行拷贝操作，否则输出日志
    if (tempFile.exists()) {
        val destFile = File(destPath)
        if (destFile.exists()) {
            // 如果目标文件存在 则提示并根据isCover参数处理当前文件（覆盖 或者 重命名）
            if (isCover) {
                destFile.delete()
            } else {
                destFile.renameTo(File(destPath + "_org"))
            }
        }
        var inputStream: InputStream? = null
        var os: FileOutputStream? = null
        try {
            inputStream = FileInputStream(tempFile)
            os = FileOutputStream(destPath)
            var len = 0
            val buffer = ByteArray(1024)
            while (inputStream.read(buffer).also { len = it } != -1) {
                os.write(buffer, 0, len)
            }
            result = true
        } catch (e: Exception) {
            Log.e("file", "copy Exception $filePath")
        } finally {
            try {
                inputStream?.close()
                os?.close()
            } catch (e: Exception) {
                Log.e("file", "copy close Exception")
            }
        }
    } else {
        Log.e("file", "copy close Exception")
    }
    return result
}