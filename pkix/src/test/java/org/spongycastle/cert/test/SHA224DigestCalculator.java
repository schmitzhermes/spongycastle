package org.spongycastle.cert.test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.SHA224Digest;
import org.spongycastle.operator.DigestCalculator;


class SHA224DigestCalculator
    implements DigestCalculator
{
    private ByteArrayOutputStream bOut = new ByteArrayOutputStream();

    public AlgorithmIdentifier getAlgorithmIdentifier()
    {
        return new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha224);
    }

    public OutputStream getOutputStream()
    {
        return bOut;
    }

    public byte[] getDigest()
    {
        byte[] bytes = bOut.toByteArray();

        bOut.reset();

        Digest sha224 = new SHA224Digest();

        sha224.update(bytes, 0, bytes.length);

        byte[] digest = new byte[sha224.getDigestSize()];

        sha224.doFinal(digest, 0);

        return digest;
    }
}
