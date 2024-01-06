package com.milkcocoa.info.colotok.core.logger

import com.milkcocoa.info.colotok.core.formatter.details.LogStructure
import com.milkcocoa.info.colotok.core.level.Level
import com.milkcocoa.info.colotok.core.level.LogLevel
import kotlinx.serialization.KSerializer

class Logger(val name: String, val config: Config) {
    constructor(name: String, config: Config.() -> Unit) : this(name = name, Config().apply(config))

    private val providers = config.providers
    private val attrs = config.defaultAttrs

    /**
     * print log with providers into passed [level]
     * @param msg[String] message to print
     * @param level[Level] log level
     */
    fun at(level: Level, msg: String){
        if(attrs.isEmpty()){
            providers.forEach {
                it.write(name, msg, level)
            }
        }else{
            providers.forEach {
                it.write(name, msg, level, attrs)
            }
        }
    }

    /**
     * print log and attrs with providers into passed [level]
     * @param level[Level] log level
     * @param msg[String] message to print
     * @param attr[Map] additional attrs
     */
    fun at(level: Level, msg: String, attr: Map<String, String>) {
        providers.forEach {
            it.write(name, msg, level, attr.plus(this.attrs))
        }
    }

    /**
     * print structured log with providers into passed [level]
     * @param msg[LogStructure] message to print
     * @param serializer[KSerializer] serializer which used for serialize [msg]
     */
    fun<T: LogStructure> at(level: Level, msg: T, serializer: KSerializer<T>) {
        if(this.attrs.isEmpty()){
            providers.forEach {
                it.write(name, msg, serializer, level)
            }
        }else{
            providers.forEach {
                it.write(name, msg, serializer, level, this.attrs)
            }
        }
    }

    /**
     * print structured log and attrs with providers into passed [level]
     * @param msg[LogStructure] message to print
     * @param serializer[KSerializer] serializer which used for serialize [msg]
     * @param attr[Map] additional attributes
     */
    fun<T: LogStructure> at(level: Level, msg: T, serializer: KSerializer<T>, attr: Map<String, String>) {
        providers.forEach {
            it.write(name, msg, serializer, level, attr.plus(this.attrs))
        }
    }



    /**
     * print trace level log with providers
     * @param msg[String] message to print
     */
    fun trace(msg: String) {
        at(LogLevel.TRACE, msg)
    }

    /**
     * print debug level log with providers
     * @param msg[String] message to print
     */
    fun debug(msg: String) {
        at(LogLevel.DEBUG, msg)
    }

    /**
     * print info level log with providers
     * @param msg[String] message to print
     */
    fun info(msg: String) {
        at(LogLevel.INFO, msg)
    }

    /**
     * print warn level log with providers
     * @param msg[String] message to print
     */
    fun warn(msg: String) {
        at(LogLevel.WARN, msg)
    }

    /**
     * print error level log with providers
     * @param msg[String] message to print
     */
    fun error(msg: String) {
        at(LogLevel.ERROR, msg)
    }






    /**
     * print trace level log with providers
     * @param msg[String] message to print
     * @param attr[Map] additional attrs
     */
    fun trace(msg: String, attr: Map<String, String>) {
        at(LogLevel.TRACE, msg, attr)
    }

    /**
     * print debug level log with providers
     * @param msg[String] message to print
     * @param attr[Map] additional attrs
     */
    fun debug(msg: String, attr: Map<String, String>) {
        at(LogLevel.DEBUG, msg, attr)
    }

    /**
     * print info level log with providers
     * @param msg[String] message to print
     * @param attr[Map] additional attrs
     */
    fun info(msg: String, attr: Map<String, String>) {
        at(LogLevel.INFO, msg, attr)
    }

    /**
     * print warn level log with providers
     * @param msg[String] message to print
     * @param attr[Map] additional attrs
     */
    fun warn(msg: String, attr: Map<String, String>) {
        at(LogLevel.WARN, msg, attr)
    }

    /**
     * print error level log with providers
     * @param msg[String] message to print
     * @param attr[Map] additional attrs
     */
    fun error(msg: String, attr: Map<String, String>) {
        at(LogLevel.ERROR, msg, attr)
    }





    /**
     * print trace level log with providers
     * @param msg[LogStructure] message to print
     * @param serializer[KSerializer] serializer which used for serialize [msg]
     */
    fun<T: LogStructure> trace(msg: T, serializer: KSerializer<T>) {
        at(LogLevel.TRACE, msg, serializer)
    }


    /**
     * print debug level log with providers
     * @param msg[LogStructure] message to print
     * @param serializer[KSerializer] serializer which used for serialize [msg]
     */
    fun<T: LogStructure> debug(msg: T, serializer: KSerializer<T>) {
        at(LogLevel.DEBUG, msg, serializer)
    }


    /**
     * print info level log with providers
     * @param msg[LogStructure] message to print
     * @param serializer[KSerializer] serializer which used for serialize [msg]
     */
    fun<T: LogStructure> info(msg: T, serializer: KSerializer<T>) {
        at(LogLevel.INFO, msg, serializer)
    }


    /**
     * print warn level log with providers
     * @param msg[LogStructure] message to print
     * @param serializer[KSerializer] serializer which used for serialize [msg]
     */
    fun<T: LogStructure> warn(msg: T, serializer: KSerializer<T>) {
        at(LogLevel.WARN, msg, serializer)
    }


    /**
     * print error level log with providers
     * @param msg[LogStructure] message to print
     * @param serializer[KSerializer] serializer which used for serialize [msg]
     */
    fun<T: LogStructure> error(msg: T, serializer: KSerializer<T>) {
        at(LogLevel.ERROR, msg, serializer)
    }




    /**
     * print trace level log with providers
     * @param msg[LogStructure] message to print
     * @param serializer[KSerializer] serializer which used for serialize [msg]
     * @param attr[Map] additional attributes
     */
    fun<T: LogStructure> trace(msg: T, serializer: KSerializer<T>, attr: Map<String, String>) {
        at(LogLevel.TRACE, msg, serializer, attr)
    }


    /**
     * print debug level log with providers
     * @param msg[LogStructure] message to print
     * @param serializer[KSerializer] serializer which used for serialize [msg]
     * @param attr[Map] additional attrs
     */
    fun<T: LogStructure> debug(msg: T, serializer: KSerializer<T>, attr: Map<String, String>) {
        at(LogLevel.DEBUG, msg, serializer, attr)
    }


    /**
     * print info level log with providers
     * @param msg[LogStructure] message to print
     * @param serializer[KSerializer] serializer which used for serialize [msg]
     * @param attr[Map] additional attrs
     */
    fun<T: LogStructure> info(msg: T, serializer: KSerializer<T>, attr: Map<String, String>) {
        at(LogLevel.INFO, msg, serializer, attr)
    }


    /**
     * print warn level log with providers
     * @param msg[LogStructure] message to print
     * @param serializer[KSerializer] serializer which used for serialize [msg]
     * @param attr[Map] additional attrs
     */
    fun<T: LogStructure> warn(msg: T, serializer: KSerializer<T>, attr: Map<String, String>) {
        at(LogLevel.WARN, msg, serializer, attr)
    }


    /**
     * print error level log with providers
     * @param msg[LogStructure] message to print
     * @param serializer[KSerializer] serializer which used for serialize [msg]
     * @param attr[Map] additional attrs
     */
    fun<T: LogStructure> error(msg: T, serializer: KSerializer<T>, attr: Map<String, String>) {
        at(LogLevel.ERROR, msg, serializer, attr)
    }





    /**
     * create trace level scope.
     * you can use [LevelScopedLogger.print] in this block.
     * which method print with providers in trace level
     * @param block action in scope
     * @see LevelScopedLogger
     */
    inline fun atTrace(block: LevelScopedLogger.() -> Unit){
        at(LogLevel.TRACE, block)
    }


    /**
     * create debug level scope.
     * you can use [LevelScopedLogger.print] in this block.
     * which method print with providers in trace level
     * @param block action in scope
     * @see LevelScopedLogger
     */
    inline fun atDebug(block: LevelScopedLogger.() -> Unit){
        at(LogLevel.DEBUG, block)
    }


    /**
     * create info level scope.
     * you can use [LevelScopedLogger.print] in this block.
     * which method print with providers in trace level
     * @param block action in scope
     * @see LevelScopedLogger
     */
    inline fun atInfo(block: LevelScopedLogger.() -> Unit){
        at(LogLevel.INFO, block)
    }


    /**
     * create warn level scope.
     * you can use [LevelScopedLogger.print] in this block.
     * which method print with providers in trace level
     * @param block action in scope
     * @see LevelScopedLogger
     */
    inline fun atWarn(block: LevelScopedLogger.() -> Unit){
        at(LogLevel.WARN, block)
    }


    /**
     * create error level scope.
     * you can use [LevelScopedLogger.print] in this block.
     * which method print with providers in trace level
     * @param block action in scope
     * @see LevelScopedLogger
     */
    inline fun atError(block: LevelScopedLogger.() -> Unit){
        at(LogLevel.ERROR, block)
    }


    /**
     * create specified level scope.
     * you can use [LevelScopedLogger.print] in this block.
     * which method print with providers in trace level
     * @param block action in scope
     * @see LevelScopedLogger
     */
    inline fun at(level: Level, block: LevelScopedLogger.() -> Unit){
        block(LevelScopedLogger(name, config, level))
    }
}