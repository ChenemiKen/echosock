package org.chenemiken;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOServer;
import org.jpos.iso.ServerChannel;
import org.jpos.iso.channel.XMLChannel;
import org.jpos.iso.packager.XMLPackager;
import org.jpos.util.LogSource;
import org.jpos.util.Logger;
import org.jpos.util.SimpleLogListener;

import java.io.IOException;

public class Main{
    public static void main(String[] args) throws ISOException, IOException {
        System.out.println("Hello world!");
        Logger logger = new Logger();
        logger.addListener(new SimpleLogListener(System.out));
        ServerChannel channel = new XMLChannel(new XMLPackager());
        ((LogSource)channel).setLogger(logger, "test-channel");
        ISOServer server = new ISOServer(8000, channel, null);
        server.setLogger(logger, "server");
        server.addISORequestListener(new RequestListener());
        new Thread(server).start();
    }
}