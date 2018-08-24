/*
 * COPYRIGHT 2015 SEAGATE LLC
 *
 * THIS DRAWING/DOCUMENT, ITS SPECIFICATIONS, AND THE DATA CONTAINED
 * HEREIN, ARE THE EXCLUSIVE PROPERTY OF SEAGATE TECHNOLOGY
 * LIMITED, ISSUED IN STRICT CONFIDENCE AND SHALL NOT, WITHOUT
 * THE PRIOR WRITTEN PERMISSION OF SEAGATE TECHNOLOGY LIMITED,
 * BE REPRODUCED, COPIED, OR DISCLOSED TO A THIRD PARTY, OR
 * USED FOR ANY PURPOSE WHATSOEVER, OR STORED IN A RETRIEVAL SYSTEM
 * EXCEPT AS ALLOWED BY THE TERMS OF SEAGATE LICENSES AND AGREEMENTS.
 *
 * YOU SHOULD HAVE RECEIVED A COPY OF SEAGATE'S LICENSE ALONG WITH
 * THIS RELEASE. IF NOT PLEASE CONTACT A SEAGATE REPRESENTATIVE
 * http://www.seagate.com/contact
 *
 * Original author:  Arjun Hariharan <arjun.hariharan@seagate.com>
 * Original creation date: 17-Sep-2015
 */
package com.seagates3.util;

import java.util.Random;

public class KeyGenUtil {
    /*
     * TODO
     * UserId and userAccessKeyIds are generated from uuid encoding it to base 64.
     * Replace it with a better approach in the next release.
     */

    /**
     * Generate a new unique Id. The first character of the ID should not be a
     * hyphen or an underscore.
     *
     * @return UserId
     */
    public static String createUserId() {
        String id = BinaryUtil.base64UUID().substring(0, 22);
        if (id.startsWith("-") || id.startsWith("_")) {
            id = getRandomChar() + id.substring(1);
        }

        return id;
    }

    /**
     * Generate a new Access Key Id for the user. The first character of the ID
     * should not be a hyphen or an underscore.
     *
     * TODO Since the Access Key Id is generated by encoding uuid to Base 64,
     * the length equals to 22 characters while AWS access key Ids are 20
     * characters. Improve the Access Key generator to generate 20 character
     * long access key id. The access key id can be generated based on
     * geographical location or other parameters.
     *
     * @return AccessKeyId
     */
    public static String createUserAccessKeyId() {
        String id = BinaryUtil.base64UUID().substring(0, 22);
        if (id.startsWith("-") || id.startsWith("_")) {
            id = getRandomChar() + id.substring(1);
        }

        return id;
    }

    /**
     * Generate a secret key for the user.The first character of the ID should
     * not be a hyphen or an underscore.
     *
     * @return SecretKey
     */
    public static String generateSecretKey() {
        byte[] digest = BinaryUtil.hashSHA256(BinaryUtil.getRandomUUIDAsByteArray());

        return BinaryUtil.encodeToBase64String(digest).substring(0, 40);
    }

    /**
     * Generate a new session Id for the temporary user (federated user).
     *
     * @param strToEncode
     * @return SessionId
     */
    public static String createSessionId(String strToEncode) {
        String id = BinaryUtil.base64EncodedHash(strToEncode);
        if (id.startsWith("-") || id.startsWith("_")) {
            id = getRandomChar() + id.substring(1);
        }

        return id;
    }

    /**
     * Return Base 64 encoded UUID.
     *
     * @return Unique ID.
     */
    public static String createId() {
        String id = BinaryUtil.base64UUID().substring(0, 22);
        if (id.startsWith("-") || id.startsWith("_")) {
            id = getRandomChar() + id.substring(1);
        }

        return id;
    }

    /**
     * Generate random character
     * @return random character in the range A..Z
     */
    private static char getRandomChar() {
        Random random = new Random();
        char c = (char)(random.nextInt(26) + 65);

        return c;
    }
}