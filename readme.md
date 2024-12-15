# CISE Protocol Converters
This project aims to provide the appropriate libraries for required conversions between CISE and other (AIS, KML etc.) protocols.

Each conversion library aims to expose an entry point that can be used to perform the required translation

In order for the CISE Protocol Converters project to be built, the CISE libraries are required. The CISE stakeholders can request access to the cise-core-repo repository by sending an email to mss@emsa.europa.eu

## Current versions

| Library | version | readme file |
--- | --- | --- |
cise-protocol-converters | 1.0.0 |  
cise-converters-common | 1.0.0 |
kml-translation-library | 1.0.0 | [kml-converter-library.md](kml-converter-library/kml-converter-library.md)
ais-translation-library | 1.0.0 | [ais-converter-library.md](ais-converter-library/ais-converter-library.md)

## Technical Details

### How to Build the project
To build the project you can execute the following command
```shell
mvn clean install
```
If you need to generate the `target/generated-sources/license/THIRD-PARTY.txt` then you need to enable the maven profile `gen-third-party` by executing the following command:
```shell
mvn clean install -Dgen-third-party
```

### Logging Mechanism
Throughout the library, the sl4j logging facade is used. The implementors that adopt this library should provide a concrete logging mechanism in their classpath (e.g. logback)

### Maven Local Repo
This project uses a maven local repo so that it can be built without immediate access to the CISE libraries. In order to gather all dependencies and update the local repo, the maintainers of the project must remember to run the following command to gather all dependencies inside the local-repo

```
mvn org.apache.maven.plugins:maven-dependency-plugin:2.9:copy-dependencies -DincludeGroupIds=eu.europa.ec.jrc.marex -DoutputDirectory=../local-repo/releases -Dmdep.useRepositoryLayout=true -Dmdep.copyPom=true -Dmdep.addParentPoms=true -DexcludeArtifactIds=kml-translation-library -DoverWriteReleases=true
```
Also, please keep in mind, that in order to use the local-repo to build this library the maven `settings.xml` should refrain from using `mirrorof *` mirror definitions otherwise the libraries will not be found when the project is built.  
