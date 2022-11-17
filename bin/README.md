# JKS for SSL certs for two way SSL configured APIs

Below notes for situation where API require certificates for two way SSL connections.
//generate .p12 file
//Copy the cert and pem file to openssl bin location and run below command.ignore the unable to write 'random state' error msg.
			openssl pkcs12 -export -in <<cert.pem>> -inkey <<key.pem>> -certfile <<cert.pem>> -out <<keyAndCertBundle.p12>>
			
note : If openssl command is not working download openssl and set the open.exe path in path variable for open ssl to be recognized globally.

//generate .jks out of .p12 file
//Copy the .p12 file to below location where cert and key are present and run below command
			keytool -importkeystore -srckeystore <<keyAndCertBundle.p12>> -srcstoretype PKCS12 -destkeystore <<destination C:\certs\keyAndCertBundle.jks>>
			

Example
===========
			
//generate .p12 file
openssl pkcs12 -export -in prvfapi.arcot.com.cer.pem -inkey prvfapi.arcot.com.key.pem -certfile prvfapi.arcot.com.cer.pem -out prvf_keyAndCertBundle.p12

//generate .jks out of .p12 file
keytool -importkeystore -srckeystore C:\Users\rg030672\Documents\PaySec-2-KT\CallBackServices\certs\FIS_certs\prvf_keyAndCertBundle.p12 -srcstoretype PKCS12 -destkeystore C:\Users\rg030672\Documents\PaySec-2-KT\CallBackServices\certs\FIS_certs\prvf_keyAndCertBundle.jks
