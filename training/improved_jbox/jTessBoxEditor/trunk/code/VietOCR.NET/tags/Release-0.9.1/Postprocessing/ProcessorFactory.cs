/**
 * Copyright @ 2008 Quan Nguyen
 * 
 * Licensed under the Apache License, Version 2.0 (the &quot;License&quot;);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an &quot;AS IS&quot; BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/
namespace VietOCR.NET.Postprocessing
{
    using System;

    public enum ISO639
    {
        eng, deu, fra, ita, nld, por, spa, vie
    }    
    
    /**
     *
     * @author Quan Nguyen (nguyenq@users.sf.net)
     */

    public class ProcessorFactory
    {
        // ISO369-3 codes: http://www.sil.org/iso639-3/codes.asp


        public static IPostProcessor createProcessor(ISO639 code)
        {
            IPostProcessor processor;

            switch (code)
            {
                case ISO639.eng:
                    processor = new EngPP();
                    break;
                case ISO639.vie:
                    processor = new ViePP();
                    break;
                default:
                    processor = new EngPP();
                    break;
                    //throw new Exception(code.ToString());
            }

            return processor;
        }
    }
}