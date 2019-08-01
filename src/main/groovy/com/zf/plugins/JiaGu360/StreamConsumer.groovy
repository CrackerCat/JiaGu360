package com.zf.plugins.JiaGu360

import java.nio.charset.Charset

class StreamConsumer extends Thread {
    InputStream is
    String type
    Closure closure
    String charset
    List<String> resultList= new ArrayList<>()

    StreamConsumer(InputStream is, String type, Closure closure) {
        this(is, type, Charset.defaultCharset().name(), closure)
    }

    StreamConsumer(InputStream is, String type, String charset, Closure closure) {
        this.is = is
        this.type = type
        this.closure = closure
        this.charset = charset
    }


    public void run() {
        try {
            is.eachLine(charset) { line ->

                resultList.add(line)

                if (line?.trim()) {
                    if (closure) {
                        closure(type, line)
                    }
                }
            }

        } catch (Exception ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (is) {
                    is.close()
                }
            } catch (Exception ex) {
                ex.printStackTrace()
            }
        }
    }

    List<String> getInputText() {
        return resultList
    }
}
