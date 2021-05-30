# AWS-ARCHITECTURE-VIDEO-STREAMING-APPLICATION
Streaming Videos Service is a multimedia outlet,that is presented to customer who isalways receiving data even whien being dispatched through a middle man.The amazon-web-services are a huge compilation of distant processing cloud servicing that cometogether to be known as a AWS platform, which is provided by amazon through theInternet.One of the essential and important of the services are the Amazon s3 and theAmazon EC2.   Compared with building a server farm on an all-in-one machine,  theproducts provided are faster, more scalable, and lower in cost.  In modern machines,media streaming is done through computer systems and is not always usable by ordinarypeople.Therefore, the device is designed so that anyone using a smart phone can accessthe media streaming content.

# Architecture Diagram
![alt text](https://github.com/SanjayKumarKKR/AWS-ARCHITECTURE-VIDEO-STREAMING-APPLICATION/blob/4b13629041b1ef886cdf94207ff2a56ff9357ae7/Screenshots/architecture.jpg)
1.  AWS S3:  Amazon Simple Storage Service may be a carrier presented via way ofmeans of Amazon Web Ser-vices (AWS) that offers item garage via an online carrierinterface.  It acts as a storage bucket to keep up the vital objects.  This facilitates us tostay the films that are to be streamed.
2. AWS LAMBDA: This carrier facilitates us to run code for any backend carrier allwith zero administration.
3. AWS Elastic Transcoder: You can use Amazon Elastic Transcoder to transcode me-dia in the cloud.  It aims to provide developers and businesses with a highly scalable,easy-to-use and affordable way to convert (or "transcode") media files from their origi-nal format to be available on smartphones, tablets, and devices.
4. AWS  Simple  Notification  Service:Simple  Notification  Service  (SNS)  It  can  be  across-system and cross-application messaging service (A2P) that is fully managed viaSMS and email.5.  AWS CloudFront:  Amazon CloudFront is a web-based service that securely sends data and also various forms of videos, applications, and APIs to a wide range of cus-tomers all over the globe with minimized latency, highest transfer rates and all thesewithin developer supportive and friendly environment.


# Working
First upload the video files into the S3 bucket using the Admin Android Application.As soon as the video is successfully uploaded the lambda function gets triggered then itinvokes the Elastic transcoder pipeline which takes the video file as input and formatsit into mpeg format and uploads the formatted video into another S3 bucket locatedin backend by AWS Cloudfront.Firebase Database is used to store the details of themovies that acts as a meta-data of the videos uploaded.After the Elastic transcoder hastranscoded the video file it send an email to the Admin through an email.  The Clients who want to watch the video can access the video through the Client Android Application.

# ScreenShots
<p margin-left: 30px;>
  <img src="https://github.com/SanjayKumarKKR/AWS-ARCHITECTURE-VIDEO-STREAMING-APPLICATION/blob/3a88d2bd3e929335a3dc50b539bd39163b07ba52/Screenshots/AF1.png" width="250" />
  <img src="https://github.com/SanjayKumarKKR/AWS-ARCHITECTURE-VIDEO-STREAMING-APPLICATION/blob/1b31d9004e3619fbee5150983a1b0d79bb93babd/Screenshots/AF2.png" width="250" /> 
  <img src="https://github.com/SanjayKumarKKR/AWS-ARCHITECTURE-VIDEO-STREAMING-APPLICATION/blob/1b31d9004e3619fbee5150983a1b0d79bb93babd/Screenshots/Af3.png" width="250" />
</p>

<p>
  <img src="https://github.com/SanjayKumarKKR/AWS-ARCHITECTURE-VIDEO-STREAMING-APPLICATION/blob/d8ed368ec2f9430b420789a7c6cfc2312a7c76cc/Screenshots/c2.png" width="250" />
  <img src="https://github.com/SanjayKumarKKR/AWS-ARCHITECTURE-VIDEO-STREAMING-APPLICATION/blob/d8ed368ec2f9430b420789a7c6cfc2312a7c76cc/Screenshots/c3.png" width="250" /> 
  <img src="https://github.com/SanjayKumarKKR/AWS-ARCHITECTURE-VIDEO-STREAMING-APPLICATION/blob/d8ed368ec2f9430b420789a7c6cfc2312a7c76cc/Screenshots/r1.png" width="250" />
</p>
