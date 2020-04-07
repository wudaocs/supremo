package com.td.utils

import com.td.log.L
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream


/**
 * 拷贝文件到应用私有目录
 */
fun copyFileToDir() {

}

/**
 * 拷贝文件到sd下
 */
fun copyFileToSD() {

}

/**
 * 拷贝文件到指定目录
 */
fun copyFile(filePath: String, destPath: String, isCover: Boolean = true): Boolean {
    L.e("file", "copy 源文件 $filePath")
    L.e("file", "copy 目标文件 $destPath")

//    加载插件 /storage/emulated/0/plugin.apk
//    加载插件======== /data/user/0/com.dn_alan.myapplication/app_plugin/plugin.apk
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
            val create = destFile.run {
                parentFile?.mkdirs()
                createNewFile()
            }
            L.e("file", "创建待写入文件 $create")
            // 传入文件地址参数并不能创建文件 所以又重新执行创建文件
            os = FileOutputStream(destPath)
            var len = 0
            val buffer = ByteArray(1024)
            while (inputStream.read(buffer).also { len = it } != -1) {
                os.write(buffer, 0, len)
            }
            os.flush()
            result = true
        } catch (e: Exception) {
            L.e("file", "copy Exception $filePath")
            e.printStackTrace()
        } finally {
            try {
                inputStream?.close()
                os?.close()
            } catch (e: Exception) {
                L.e("file", "copy close Exception")
                e.printStackTrace()
            }
        }
    } else {
        L.e("file", "copy close Exception")
    }
    return result
}