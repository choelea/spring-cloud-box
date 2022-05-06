package tech.icoding.util;

import lombok.SneakyThrows;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;

/**
 * @Auther: GaoDong
 * @Date: 2021/9/7
 * @Description: 国密4加密
 */
public class Sm4Utils {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private static final String ENCODING = "UTF-8";
    public static final String ALGORITHM_NAME = "SM4";

    //加密算法/分组加密模式/分组填充方式
    //PKCS5Padding-以8个字节为一组分组加密
    //定义分组加密模式使用：PKCS5Padding

    public static final String ALGORITHM_NAME_ECB_PADDING = "SM4/ECB/PKCS5Padding";

    //128-32位16进制;256-64位16进制

    public static final int DEFAULT_KEY_SIZE = 128;

    /**
     * 生成ECB暗号
     *
     * @param algorithmName 算法名称
     * @param mode          模式
     * @param key
     * @return
     * @throws Exception
     * @explain ECB模式（电子密码本模式：Electronic codebook）
     */
    private static Cipher generateEcbCipher(String algorithmName, int mode, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithmName, BouncyCastleProvider.PROVIDER_NAME);
        Key sm4Key = new SecretKeySpec(key, ALGORITHM_NAME);
        cipher.init(mode, sm4Key);
        return cipher;
    }

    /**
     * 生成秘钥
     * @return
     * @throws Exception
     */
    public static String generateKey() throws Exception  {
        byte[] bytes = generateKey(DEFAULT_KEY_SIZE);
        StringBuilder buf = new StringBuilder(bytes.length * 2);
        for(byte b : bytes) { // 使用String的format方法进行转换
            buf.append(String.format("%02x", new Integer(b & 0xff)));
        }
        return buf.toString();
    }

    public static byte[] generateKey(int keySize) throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM_NAME, BouncyCastleProvider.PROVIDER_NAME);
        kg.init(keySize, new SecureRandom());
        return kg.generateKey().getEncoded();
    }

    /**
     * sm4加密
     *
     * @param hexKey   16进制秘钥（忽略大小写）
     * @param paramStr 待加密字符串
     * @return 返回16进制的加密字符串
     * @throws Exception
     */
    @SneakyThrows
    public static String encryptEcb(String hexKey, String paramStr){
        String cipherText = "";
        // 16进制字符串-->byte[]
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        // String-->byte[]
        byte[] srcData = paramStr.getBytes(ENCODING);
        // 加密后的数组
        byte[] cipherArray = encrypt_Ecb_Padding(keyData, srcData);
        // byte[]-->hexString
        cipherText = ByteUtils.toHexString(cipherArray);
        return cipherText;
    }

    /**
     * 加密模式之Ecb
     *
     * @param key
     * @param data
     * @return
     * @throws Exception
     * @explain
     */
    public static byte[] encrypt_Ecb_Padding(byte[] key, byte[] data) throws Exception {
        Cipher cipher = generateEcbCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    /**
     * sm4解密
     *
     * @param hexKey     16进制密钥
     * @param cipherText 16进制的加密字符串（忽略大小写）
     * @return 解密后的字符串
     * @throws Exception
     * @explain 解密模式：采用ECB
     */
    @SneakyThrows
    public static String decryptEcb(String hexKey, String cipherText){
        // 用于接收解密后的字符串
        String decryptStr = "";
        // hexString-->byte[]
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        // hexString-->byte[]
        byte[] cipherData = ByteUtils.fromHexString(cipherText);
        // 解密
        byte[] srcData = decrypt_Ecb_Padding(keyData, cipherData);
        // byte[]-->String
        decryptStr = new String(srcData, ENCODING);
        return decryptStr;
    }

    /**
     * @param key
     * @param cipherText
     * @return
     * @throws Exception
     */
    public static byte[] decrypt_Ecb_Padding(byte[] key, byte[] cipherText) throws Exception {

        Cipher cipher = generateEcbCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(cipherText);
    }

    /**
     * 校验加密前后的字符串是否为同一数据
     *
     * @param hexKey     16进制密钥（忽略大小写）
     * @param cipherText 16进制加密后的字符串
     * @param paramStr   加密前的字符串
     * @return 是否为同一数据
     * @throws Exception
     * @explain
     */
    @SneakyThrows
    public static boolean verifyEcb(String hexKey, String cipherText, String paramStr){
        // 用于接收校验结果
        boolean flag = false;
        // hexString-->byte[]
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        // 将16进制字符串转换成数组
        byte[] cipherData = ByteUtils.fromHexString(cipherText);
        // 解密
        byte[] decryptData = decrypt_Ecb_Padding(keyData, cipherData);
        // 将原字符串转换成byte[]
        byte[] srcData = paramStr.getBytes(ENCODING);
        // 判断2个数组是否一致
        flag = Arrays.equals(decryptData, srcData);
        return flag;
    }


    public static void main(String[] args) throws Exception{
        String key = "b131fc7009d604ff80d9b760fb52c0a2";
        System.out.println("key = "+key);
        String s = encryptEcb(key, "{\"userName\": \"znck-test\"}");
        System.out.println("pre == "+s);
        System.out.println("post == "+decryptEcb(key,"02c88b24629c09d881bd9a3c9f6722eccb797e018ef099744492f78b3a36b100f01cb74c7070dbc05570e266032b19742fcdfd8544782d070a4a489f38277785501020b24d04a0e0434d40c0709e03203ea32af1719adfc309e1c8e9d5d60056090f43a8e5dde59105fbb60062079ffc860e89106230146eea8680e90a43303b513e6612ab2b39ffe2bec0a68abb4b32c29d66ff203581c4490285e651d4c5f2165e529c56005d6d8ca93ce796fddc52cc731c5daa22d557953894b48cc9dd9f957c9f07804dd86cea5fb2cdbffa69917ba07e7ed45d924fecd564bae3d106452cf64843ccc605562fa19265b92b90da62857671abce412322df64d09488c952dbeac248276ba4f320fdaf52e396fa5dfe376a662cf62c3260af47400c629034cfc43ed31404aa96fcc3da719475f4afadc6312e2c11051e39bd4deb99789de17caaf8da50c4d67ef89202a7aa0d0b3eea02c6b1057c24ce4c9891ca30938bd42761efc39f4522a8ed8a4556fb159940cc9b13cfddcaa4546641aca2517d2e70d29409d3b908fc0bc3f81745f7dc1867475c1fb3f8df32dd9d8ee579cbd49a3395d4428ca3815be4f9fb8e0e3b29074964ca6b5c79df8150d8d1daf45f2002fb05c0eb548e8d83fe41ce89528ba05eba1d1a9fe0e821c390b11b24e4b409f4e7ce5514f60c789f40b5277e39318f690a647dc55ef807777e70efab262ae98d65c9dbfd8225f9757c63886ae2eb3e5ed5b7c7502406a8ab9e9fc88332a3b6c1bf25dbd00d0094421cbe6da630e12fdce4796b8113f9708abd3d1b3c466ff608c6d42d292974465b2fa68d0e98a9ff42fd7dcd1220553d00d1016d4bcfb33dd79b763ce1df8bf579f45569a46e7ac97e3163f3c452651ea827002334fc5c02a296bdced5387f35c742acafd02d2ee6426fb9ef3b28a25c25a0ad99aa522329d37376d6e4898a8981b0e08b2472fc8b4e65bb4816e3c631a1f5e139fe136994d7ef80708414991718c2722281f61d954f0f9e88d9a79bb7b5a1905865eec6f51fea6b1573c214789b5451d2e18ab74c4b20b8d79b70c0df555ebf82762f718a01d7e5246338a4e56135f0f110c612e7f81c31d6a1e73fd1123a52c3d14538d0e9cd960000b6dc791def772a95cdd7430cabe37354a3845a6ca4809ec7e457dbf1099bdc177b1cbafc2d8536de489bc8f7f69c0a8fb54065dd21e0c42c85e81d057acc0a3e6c4f8f76009e4d300127149b25e542fa8d674267bbb27a7c157a76ffc8058cc26c2bc9241886de33aa7e1f670932f801ae90264a4b73d3956cd2bad291e18bc3b9304651f6a42b73fd414a280b36196e400b598671493f5ed5f60f081523c9bb5fdfb7cb70aeda57256c6e2c2224e8436018bc6fc66f266fca4a2079b063c80240c6822c52595142566ca083e9864b81bdf43f80569d0829f7e3c28be4f11c85bc793c166ed2dbeaa1b24856d53993e912d80e30c01fddfd376c021363a270c09e88b77ca92b87a8f9b5788823055d630115e8dfa3a70c2429a96a6e7819ae6bc9fb5fe29752291e1e6d927397b0f3eaf56106df6138bb0455155782f2f934248a6027240e82d33cdbb7c6e91eda43898909261a4db328ebf0101bb958edb3893f4372bccfa7b029fc458c55189e88d9a79bb7b5a1905865eec6f51feab4eb949e07231c87fd40159476334085b8d79b70c0df555ebf82762f718a01d78600e1522222620ffc875f6b4a08322b06bdfb0db6f3a2058e24abdce7cdf8512147a973dffe5f3f5f021359691acc0bfb139d187297eb5a906e5e3e0de2f14c35828028c16fcd2c0a7131983bc877be4ba1207a0fb0ab7053c54ba6b0cf6111c6dc7d41c7edd5c57776bbe9d1518682a32db1fef07ba25d8ee086f1839d4f277d5ae63cb217604d7937c80bc3ca3dc6a0e84a0fd3721b441db089402629af01b7d504541252f557b2cde7422ca060704af9e12a3f4a0bbd0b84405abc4c1a301b5808f6a1209f3ec29c0f67c123aa3f61312ac4fe9d81d8f3d1c1ff6b7f178d9fe043d0623c72ca5776268b963dd0fdb192f51b5d454edebcd20cf27d4ce0e830b7a49dac3b771682230d2246b10a65475c1fb3f8df32dd9d8ee579cbd49a336e9d85856f5c979da596333fe657e751f51d7c7161fdd69e30f1e4c95c8fb34ac6dc7d41c7edd5c57776bbe9d1518682b6380cf67e77f0c2aaf8197859f22c06fad226b1dad31c46de06252878c747c4cd73958049a180ff0d5029512aa0ebf4ffc460015b34431d1bea0554b0adb9e39e799eda614e3534d95be2d3054eae499e88d9a79bb7b5a1905865eec6f51fea6b1573c214789b5451d2e18ab74c4b20b8d79b70c0df555ebf82762f718a01d76b7c13534ba8917aa04928a52bab9fa99c14fd6f590bc41fa4abf20ed9a53533348eab4187a555ff311b9bb023bc08154c17bb4ab7b085ac1ddc93e550c69bc4f0663e895ebd6dfb5baa23f0bf6d42d0349022c9ebb63196b045c2d68dc3d44e0ef74acd0269de4959c89066b80c7c58679a31744a18f768f9b670647a5d4eba91d19f6bd3286249271241474b051b43363d0ec3564eae665a57ed25bec9c0cbabc24ba4d2af27979a24de50e1cda0f747d20c027157175fdfeb687548fab912b8d79b70c0df555ebf82762f718a01d78600e1522222620ffc875f6b4a08322b06bdfb0db6f3a2058e24abdce7cdf851e1fb99823ee594b2fc11a06159592c9e9ffc54b5bcb3c614814c62954654bbaed4709880ee9eacb0b352578c9c31e56ba3da76dd178e0b26002ee6f929a6eb8f5bdb04326f07947fc0b1f92bf100fd230bb7860a775fceead93ac3db1c4a2a63ceec64c780fe44038b1eeaae7ede21b9e858325db5df24ba414dce20253367973c2cc7dfb8c11f4a6bc334619c7783909b95258e726dc5f693b71a43001badf45beb31ff8023c3766003520e09a3a2bc3d84e08f71f46aea03a2bbe5fd98ea041f96e7830bd077cd381754786d8293ac3f94628656d74623c98fdb60d3dc59cb867e2b5b887a572930b0eaf4c5aa4e8562276419fd02a9799a0cbaf6c3b7f7914c308effe811539710ed7c0290b905d9d5a80877411738cf50dc609a589985aae4e42a8ee0cba4204f9fff20c1c1d8eb21189b01483ca3310d7f65573c6895dd89ea9e7a64eb927fd197b8db2df4156e41c40e93ec73578da6e79e3cb518df6d3fdbf3fd602b035d3891c652463d5cc1ab3baaf106dbe56adc33a1556ccdfeec5a37d7998824553c8a9ec1c31f333bf7c6665f6d322d72f34ffa1390c356a5fe5fd182f1381aad26b49b54ff5517eb1ec691398d1fe3bd6f94e81d4069de1bc31f382ff388e766e32bea0516b4b7a180fb6c02a76d6fe28091b3f20a4fc754a935ad00c1fc022517fd54a06a58f5af47438a6fd954170da8237d09bd66379248513e6612ab2b39ffe2bec0a68abb4b32c29d66ff203581c4490285e651d4c5f2edb404698524856f8990df7268d94b71137fb66034d4d8671b3450d231cef2e591f594915953808c29f97fd566cb23667ba07e7ed45d924fecd564bae3d106452cf64843ccc605562fa19265b92b90da62857671abce412322df64d09488c952f141a40e15aa6a0783cb92474adebc0e4d533737b3c1857f0792cc61b1415591bbf8cfda5144549445dfd2db7881dccd9be10209abca1da328e9931bc2f0ffc95a4e7e2199b12603b3c0f2c58495ad877dddd05407d289c17b5964acb250330837b7cb2640073e953971f56150197a0d72ac037ecfb8e904a749ee69114e81ecc5e018fbfcd4b62aaf5df9e79a567a8d71952bf6a8f4ac1638ebac66f94058191ea3910a64632b399dc30a9f2556e754ee37678fbc76be51eb5469a882ae4bc728251cdaf635217c2b3bfc2b8efbc1215c2521ae9a571c0ff3ccb387861ee8b0c6665f6d322d72f34ffa1390c356a5fed742c5e1f71b7568dce97cbaf09be460d4f915f6f85763caa84441519eaa5cf08a2b6a8aebd73e7f644c8fbfa09ec386a54d7c96a0b847fa78ca0f17d931236f34c350270e99bf41abac3e3e996d28e5560dfaf498f1f5e542d8b82779e10017f82436f774821a24f9db3f382209695e2429f8801675c11095775efd51d10467ef361ed0fb0af545fd516586174cb0ae74a69c69d64f4fe5b3273c79ce455f4e16758ed1b3a5513a528f2fe25f766ea3c3d39f70448af2424a381050f6979a99219bd515ff9370355b26817de0b60b47832fb5cc8629fd0605d38703eb2fec51dca2b06a5e19ff4381a950ca77d363e93789fa8811d45aa8499235951689ba08b7c7502406a8ab9e9fc88332a3b6c1bfdd85d9d9eda539655642b8fd2cb4f074796b8113f9708abd3d1b3c466ff608c6d42d292974465b2fa68d0e98a9ff42fd7dcd1220553d00d1016d4bcfb33dd79be471fe5a1265c33d1834fdacd82bbbd22678b65c2f6c471f661abea4711ccd520a638d8fc4f38b40e656f5d893f6b7d94446862de283996cb354539f3d11ddb6b47b731e1945520d0bd349d31fca9e7597b3056bfdd69b595e31ac5175d78994cc9b13cfddcaa4546641aca2517d2e709b1436c6714c94c5e556c1d4987a616e475c1fb3f8df32dd9d8ee579cbd49a335391ca94fa8fae6e5c5c5741b046781718c759c81cacba236cbf03b4a6249919"));
    }

}
