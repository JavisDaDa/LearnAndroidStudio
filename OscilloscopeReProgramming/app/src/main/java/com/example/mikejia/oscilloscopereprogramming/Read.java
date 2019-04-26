package com.example.mikejia.oscilloscopereprogramming;

public class Read {


    public static void ReadText(){
        //定义一个变量用于储存服务器发来的数据
        String textdata;
        //根据RD的值来判断是否执行读数据
        while(MainActivity.RD){
            try {
                //定义一个字节集,存放输入的数据,缓存区大小为2048字节
                final byte[] ReadBuffer = new byte[409600];
                //用于存放数据量
                final int ReadBufferLength;
                //从输入流获取服务器发来的数据和数据宽度
                //ReadBuffer为参考变量，在这里会改变为数据
                //输入流的返回值是服务器发来的数据宽度
                ReadBufferLength = MainActivity.InputStream.read(ReadBuffer);

                //验证数据宽度,如果为-1则已经断开了链接
                if (ReadBufferLength == -1) {
                    MainActivity.RD = false;
                    MainActivity.socket.close();
                    MainActivity.socket = null;
                } else {
                    //如果有数据正常返回则进行处理显示
                        /*
                            这个地方有个很大的坑，让我搞了不少的时间
                            我用其他语言写的Web服务器程序，默认编码是gb2312
                            AS的默认编码是utf-8
                            在获取服务器发来的数据的时候，程序已经对这段gb2312的数据进行编码...
                            至于编码是什么就不知道了
                            我研究了很长时间，怎么转码也不对，越转越乱
                            最后测试出来是gb2312编码已经被转码了，我就先恢复gb2312编码
                            然后转成程序不会乱码的utf-8
                            如果目标服务器编码是utf8的话就不用转了
                        */

                    //先恢复成GB2312编码
                    textdata = new String(ReadBuffer, 0, ReadBufferLength, "GB2312");//原始编码数据
                    String textdata5 = new String(ReadBuffer, 6, 4, "GB2312");
                    String show0 = new String(textdata.getBytes(), "UTF-8");
//                        StringBuilder show = new StringBuilder(show0);
//                        StringBuilder show1 = new StringBuilder(String.valueOf(ReadBufferLength));
//                        show = show.append("共").append(show1).append("个字符。").append("共").append(textdata5).append("个数据。");
                    //转为UTF-8编码后显示在编辑框中
                    MainActivity.receive_EditText.setText(show0);
                    if (show0.startsWith("#8000") && DataTransfer.mShow == null){
                        float[] processedData;                                      //定义处理过数据的数据
                        DataProcessing dataProcessing = new DataProcessing(show0);  //创建处理数据对象
                        processedData = dataProcessing.DataSplit();                 //将数据处理
                        if(MainActivity.data != null){                              //如果主函数中的data不为空则执行
                            for (int i = 0; i < processedData.length; i++){         //赋值
                                MainActivity.data.add(processedData[i]);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    }

//    synchronized public static float[] ReadData(){
//        float[] data = {3.21f};
//        DataProcessing dataProcessing;
//        String show0;
//        //定义一个变量用于储存服务器发来的数据
//        String textdata;
//        //根据RD的值来判断是否执行读数据
//        while (MainActivity.RD) {
//            try {
//                final byte[] ReadBuffer = new byte[409600];
//                //用于存放数据量
//                final int ReadBufferLength;
//                ReadBufferLength = MainActivity.InputStream.read(ReadBuffer);
//
//                //验证数据宽度,如果为-1则已经断开了链接
//                if (ReadBufferLength == -1) {
//                    MainActivity.RD = false;
//                    MainActivity.socket.close();
//                    MainActivity.socket = null;
//                } else {
//                    textdata = new String(ReadBuffer, 0, ReadBufferLength, "GB2312");//原始编码数据
//                    show0 = new String(textdata.getBytes(), "UTF-8");
//                    dataProcessing = new DataProcessing(show0);
//                    data = dataProcessing.DataSplit();
//                    StringBuilder show = new StringBuilder(show0);
//                    StringBuilder show1 = new StringBuilder(String.valueOf(data.length));
//                    show = show.append("共").append(show1).append("个字符。");
//                    MainActivity.receive_EditText.setText(show);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return data;
//    }
//
//    public static void ReadPreamble(){
//
//    }
}