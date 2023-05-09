package com.example.scanner.lib;

/**
 * 自定义识别接口
 *
 */
public interface IScanner {

    Result scan(byte[] data, int width, int height) throws Exception;

}
