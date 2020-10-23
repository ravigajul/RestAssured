# RestAssured

Below notes for situation where API require certificates for two way SSL connections.
//generate .p12 file
			openssl pkcs12 -export -in <<cert.pem>> -inkey <<key.pem>> -certfile <<cert.pem>> -out <<keyAndCertBundle.p12>>
			
//generate .jks out of .p12 file
			keytool -importkeystore -srckeystore <<keyAndCertBundle.p12>> -srcstoretype PKCS12 -destkeystore <<destination C:\certs\keyAndCertBundle.jks>>
