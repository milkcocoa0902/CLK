package com.milkcocoa.info.clk.core.provider.builtin

import com.milkcocoa.info.clk.core.LogLevel
import com.milkcocoa.info.clk.core.provider.details.Provider
import com.milkcocoa.info.clk.core.provider.rotation.Rotation
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
class FileProvider(val filename: String, config: FileProviderConfig) : Provider {

    /**
     * initialize provider with default configuration
     */
    constructor(filename: String): this(filename, FileProviderConfig())

    /**
     * initialize provider with specified configuration
     */
    constructor(filename: String, config: FileProviderConfig.() -> Unit): this(filename, FileProviderConfig().apply(config))
    class FileProviderConfig{
        /**
         * size of buffer in Byte
         */
        var bufferSize = 1024

        /**
         * use buffering. if enable this, provider does not write to file until buffered data exceed `bufferSize`
         */
        var enableBuffer = true

        /**
         * if not null, rotate after write
         */
        var rotation: Rotation? = null

        /**
         * log level
         */
        var logLevel: LogLevel = LogLevel.DEBUG
    }

    override val colorize: Boolean
        get() = false


    private val enableBuffer = config.enableBuffer
    private val bufferSize = config.bufferSize
    private val rotation = config.rotation
    private val logLevel = config.logLevel

    private val sb: StringBuilder = StringBuilder()
    override fun write(name: String, str: String, level: LogLevel) {
        if(level.isEnabledFor(logLevel).not()){
            return
        }
        runCatching {
            if(enableBuffer){
                sb.append(formatter.format(str.plus("\n"), level))
                if(sb.length > bufferSize){
                    BufferedOutputStream(FileOutputStream(File(filename), true)).use { bos ->
                        bos.write(sb.toString().encodeToByteArray())
                        sb.clear()
                    }
                }
            }else{
                BufferedOutputStream(FileOutputStream(File(filename), true)).use { bos ->
                    bos.write(formatter.format(str.plus("\n"), level).encodeToByteArray())
                }
            }

            if(rotation?.isRotateNeeded(filename) == true){
                rotation?.doRotate(filename)
            }
        }
    }

    fun flush(){
        BufferedOutputStream(FileOutputStream(File(filename), true)).use { bos ->
            bos.write(sb.toString().encodeToByteArray())
            sb.clear()
        }

        if(rotation?.isRotateNeeded(filename) == true){
            rotation?.doRotate(filename)
        }
    }
}