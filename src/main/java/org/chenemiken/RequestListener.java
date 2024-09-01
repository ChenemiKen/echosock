package org.chenemiken;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOSource;

import java.io.IOException;
import java.util.Arrays;

public class RequestListener implements ISORequestListener {
    @Override
    public boolean process(ISOSource isoSource, ISOMsg isoMsg) {
        try {
            byte[] msgPack = isoMsg.pack();
            System.out.println(Arrays.toString(msgPack));
            isoMsg.setResponseMTI();
            isoMsg.set(39, "00");
            isoSource.send(isoMsg);
        } catch (ISOException | IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
