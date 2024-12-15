# KML Converter Library
The main entry point of this library is the KMLConverter class. It is used to convert a CISE Message containing vessels into a KML Document containing placemarks.

# KML Translator issues
```xml
<Placemark id="vessel_1951085637">   <!-- What is this referring to? -->
    <name></name>
    <TimeStamp><when>2022-04-15T05:59:43Z</when></TimeStamp> <!-- What is this referring to? Maybe we can get the created at from teh cise message -->
    <Description></Description>
    <Point>
        <coordinates>27.3897,-90.50332333333333,0</coordinates>
    </Point>
    <ExtendedData>
        <Data name="NGL-LABEL"><value>119084-209</value></Data> <!-- What is this referring to? -->
        <Data name="UUID"><value>2a5e5ec4-6653-4b01-80af-2b7968f5fa81</value></Data> <!-- What is this referring to? -->
        <Data name="MMSI"><value>369045000</value></Data>
        <Data name="IMO"><value>0</value></Data>
        <Data name="NavStatus"><value>Under way using engine</value></Data>
        <Data name="SPEED"><value>5.658889</value></Data>
        <Data name="COURSE"><value>0.02617994</value></Data> <!-- What is this referring to? -->
        <Data name="SOURCEID"><value>2376016</value></Data> <!-- What is this referring to? -->
        <Data name="SOURCESYSTEM"><value>MSIS</value></Data> <!-- What is this referring to? -->
        <Data name="REALTIME"><value>false</value></Data> <!-- What is this referring to? -->
    </ExtendedData>
</Placemark>

vs

 <Vessel>
      <Name>Nordautumn</Name>
      <LocationRel>
        <Location>
          <Geometry>
            <Latitude>36.3663</Latitude>
            <Longitude>-8.319674</Longitude>
          </Geometry>
        </Location>
        <COG>107.0</COG>
        <Speed>16.6</Speed>  <!-- if the speed is inside the LocationRel then this means that we may have more than one, so how do we handle this with the KML?-->
      </LocationRel>
      <Breadth>32</Breadth>
      <CallSign>CQAF3</CallSign>
      <Deadweight>30</Deadweight>
      <Draught>12.2</Draught>
      <GrossTonnage>38332.0</GrossTonnage>
      <IMONumber>9323039</IMONumber>
      <Length>246.83</Length>
      <MMSI>255806157</MMSI>
      <NavigationalStatus>UnderWayUsingEngine</NavigationalStatus>
      <ShipType>GeneralCargoShip</ShipType>
      <YearBuilt>2008</YearBuilt>
    </Vessel>
```