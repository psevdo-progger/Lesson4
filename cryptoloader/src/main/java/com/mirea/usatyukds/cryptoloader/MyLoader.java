package com.mirea.usatyukds.cryptoloader;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public	class	MyLoader	extends AsyncTaskLoader<String> {
    private	String	decrypt;
    public	static	final	String	ARG_WORD	=	"word";

    public	static	String	decryptMsg(byte[]	cipherText,	SecretKey secret){
        /*	Decrypt	the	message	*/
        try	{
            Cipher cipher	=	Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE,	secret);
            return	new	String(cipher.doFinal(cipherText));
        }	catch	(NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
                       | BadPaddingException | InvalidKeyException e)	{
            throw	new	RuntimeException(e);
        }
    }
    public	MyLoader(@NonNull Context context, Bundle args)	{
        super(context);
        if	(args	!=	null) {
            byte[] cryptText = args.getByteArray(ARG_WORD);
            byte[] key = args.getByteArray("key");
            SecretKey originalKey = new SecretKeySpec(key, 0, key.length, "AES");
            decrypt = decryptMsg(cryptText, originalKey);
        }
    }
    @Override
    protected	void	onStartLoading()	{
        super.onStartLoading();
        forceLoad();
    }
    @Override
    public	String	loadInBackground()	{
        SystemClock.sleep(5000);
        return	decrypt;
    }

}