export * from "./routes";
export * from "./regex";
export * from "./table";
export * from "./geometry";

export class Utils {
  static isNullOrUndefined(object: unknown): object is null | undefined {
    return object === null || object === undefined;
  }
}
