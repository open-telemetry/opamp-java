# opamp-java
OpAMP protocol implementation in Java

## Generating the protos

The `proto` module is generated from the proto files found in the [opamp-spec](https://github.com/open-telemetry/opamp-spec)
repository. In order to update this module, change to the `generateProtos` directory and run

```bash
../gradlew build
```
