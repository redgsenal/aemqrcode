build AEM via maven:
> mvn org.apache.maven.plugins:maven-archetype-plugin:2.4:generate -DarchetypeGroupId=com.adobe.granite.archetypes -DarchetypeArtifactId=aem-project-archetype -DarchetypeVersion=11 -DarchetypeCatalog=https://repo.adobe.com/nexus/content/groups/public/

download maven repos:
> mvn clean install -U -Padobe-public

install to localhost:7502
> mvn clean install -PautoInstallPackage -Padobe-public

OSGi
> match the version number from the OSGi MANIFEST.MF with the core bundle's pom Import-Package version.  If conflict found, increment the version and match.

How to enable AEM QR Code:
1) http://localhost:7502/system/console/configMgr -> AEM WCM QR-Code Generator Servlet (com.adobe.cq.wcm.mobile.qrcode.servlet.QRCodeImageGenerator)
2) Change whitelist to ^https?:\/\/(.*) and save
3) Test: http://localhost:7502/libs/wcm/mobile/qrcode.png?url=http://gbb.sg/ahawldw
