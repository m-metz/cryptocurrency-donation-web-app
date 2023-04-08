import { XMLParser } from "fast-xml-parser";

/**
 * This code is customized from
 * https://github.com/lorenzoc25/vite-plugin-xml-loader/blob/master/index.ts.
 *
 * It uses an MIT licence https://opensource.org/license/mit/, so no problems
 * with licensing.
 *
 * Add this to your vite.config.*  file to be able to import and parse xml files.
 *
 * ```
 * import { defineConfig } from 'vite'
 * ...
 * import XMLLoader from './src/vite-plugins/xml-loader'
 *
 * ...
 *
 * export default defineConfig({
 *   plugins: [
 *     ...
 *     XMLLoader(),
 *   ]
 * })
 *```
 *
 * @export
 */
export default function XMLLoader(): {
  name: string;
  transform(
    code: string,
    id: string
  ): Promise<{ code: string } | { code?: undefined }>;
} {
  return {
    name: "xml-loader",
    async transform(code: string, id: string) {
      const xmlRegEx = /\.xml$/;
      if (xmlRegEx.test(id)) {
        /*
        Customise the XML to JS parser options here:
        https://github.com/NaturalIntelligence/fast-xml-parser/tree/master/docs/v4
        */
        const options = {
          ignoreAttributes: false,
        };
        const parser = new XMLParser(options);
        const jsObject = await parser.parse(code);
        return {
          code: `export default ${JSON.stringify(jsObject)}`,
        };
      }
      return {};
    },
  };
}
