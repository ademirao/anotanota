/**
 * Copyright @ 2009 Quan Nguyen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.sourceforge.vietocr;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class Utilities {
    /**
     * 
     * @return the directory of the running jar
     */
    public static File getBaseDir(Object aType) {
        URL dir = aType.getClass().getResource("/" + aType.getClass().getName().replaceAll("\\.", "/") + ".class");
        File dbDir = new File(System.getProperty("user.dir"));

        try {
            if (dir.toString().startsWith("jar:")) {
                dir = new URL(dir.toString().replaceFirst("^jar:", "").replaceFirst("/[^/]+.jar!.*$", ""));
                dbDir = new File(dir.toURI());
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (URISyntaxException use) {
            use.printStackTrace();
        }
        return dbDir;
    }
}