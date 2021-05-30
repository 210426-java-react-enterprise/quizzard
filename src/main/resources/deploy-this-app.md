# Deploying a Servlet-based application with AWS

## AWS Services we will use:
- AWS CodePipeline
- AWS CodeBuild
- AWS Elastic Beanstalk

## Create a deployment environment using AWS Elastic Beanstalk

Before we get into creating a CI/CD pipeline, we will start by setting up a deployment environment for our pipeline to use for its deploy stage. For this, one of AWS's many PaaS tools, Elastic Beanstalk, is perfect for this. Elastic Beanstalk (EB) is a meta-service that allocates and manages an EC2 instance on our behalf and using the platform configuration we provide to it, it will set up the EC2's environment so that we can use it as a deployment environment. 

It should be noted, that while you can see the EC2 instance(s) that EB is managing; you should not attempt to stop or terminate these instances from here. They will stop/terminate, however since EB is managing these instances it will detect that they are no longer running and simply allocate new instances. Lesson: manage your EB application's EC2 instances from the EB console.

Follow the below instructions to get your EB application set up!

### Configuration
- Go to AWS Elastic Beanstalk and choose: Create Application
- Give your application a name (e.g. quizzard)


- In the Platform section:
  - Platform: `Tomcat`
  - Platform branch: `Tomcat 8.5 with Corretto 8 running on 64bit Amazon Linux 2`
  - Platform version: `4.1.8`


- For the Application code section, simple leave the default "Sample Web Application". This way we can easily check to see if our instance was configured correctly.


- Once Elastic Beanstalk is finished initializing your instance it should redirect you to the root environment dashboard. From here, click the link that should open a new tab with a Welcome page that confirms that the EB instance was set up and configured correctly.



## Create a Pipeline using AWS CodePipeline

A CI/CD pipeline is a wonderful thing to have when developing an application. The continuous integration aspect of it helps the development team to detect broken builds more quickly and to easily run tests on the application allowing the development team's to work with more agility. The continuous deployment aspect of the pipeline is equally useful. By deploying the application to a test environment, integration and end-to-end tests can be performed, helping the development team detect runtime bugs more quickly and provide prompt patches of broken implementations. 

There are many options when it comes to CI/CD pipelines for software development. AWS, Microsoft Azure, and Google GCP all provide some built-in service that allows users to create pipelines. Additionally, some platform-independent tools exist as well such as Jenkins. For this example, we will leverage AWS's CodePipeline to facilitate the sourcing, building (using AWS CodeBuild), and deployment (to AWS Elastic Beanstalk) of our web application.

Follow the below instructions to get your pipeline set up!

### Configuration
- Go to AWS CodePipeline and choose: Create Pipeline
- Give your build project a name (e.g. quizzard-staging-pipeline)
- The default values for the remainder of the Pipeline Settings section are fine and can be left alone.


- For the Source section:
  - For Source provider, choose: `Github (Version 2)`
  - Choose an existing connection or create a new one to your GitHub account
  - Select the repository you wish to source from
  - Choose the branch from the selected repository you wish to source from
  - The default values for the remainder of this section are fine and can be left alone.
  - Click: `Next`
    

- For the Build section:
  - For Build provider, choose: `AWS CodeBuild`
  - Click `Create Project`
  - Follow the instructions for [Creating a Build Project using AWS CodeBuild](#creating-a-build-project-using-aws-codebuild)
  - The default values for the remainder of this section are fine and can be left alone.
  - Click: `Next`


- For the Deploy section:
  - For Deploy provider, choose: `AWS Elastic Beanstalk`
  - For Application name, choose the name of the Elastic Beanstalk application you set up earlier.
  - For Environment name, choose the environment created for your by Elastic Beanstalk
  - Click: `Next`


- Review the pipeline configurations you provided for accuracy.
- Click `Create Pipeline`


## Creating a Build Project using AWS CodeBuild

When we tell our pipeline to source our application's code from our remote Git repository, we probably intend to run our tests and attempt to build the project at the least. So, in order to do this we will need to have a temporary environment that will hold our source code, test it, and build it. This environment doesn't need to be continuously running, just long enough for a few simple terminal commands to execute and see the result of their output. To facilitate this build, we will use AWS's CodeBuild service.

CodeBuild (CB) is simply a service that creates a temporary runtime environment where provided source code can be build using commands found in a configuration file known as a *Buildspec*. This *Buildspec* can be included in our source code directory (named: `buildspec.yml`) or the configuration for it can be provided inline during build project creation on the AWS Console. For this example, we will opt to include our *Buildspec* commands inline during build project creation.

Follow the below instructions to get your build project set up!

### Configuration
- Go to AWS CodeBuild and choose: Create Build Project
  

- In the Project Configuration section: 
  - Give your build project a name (e.g. quizzard-build)
  - The default values for the remainder of this section are fine and can be left alone.
    

- In the Environment section:
  - Leave the default value of `Managed Image` for the Environment image
  - Operating system: `Ubuntu`
  - Runtime: `Standard`
  - Image: `aws/codebuild/standard:5.0`
  - Image version: `Always use the latest image for this runtime version`
  - Environment type: `Linux`
  - The default values for the remainder of this section are fine and can be left alone.
    

- In the Buildspec section
  - Choose: `Insert build commands`
  - Select: `Switch to editor`
  - Delete the default values in the editor and paste in the below:
    
```yaml
version: 0.2

phases:
  install:
    runtime-versions:

      # Correto is basically Amazon's in-house version of Java that they main, it is 
      # compatible with project's built using native Java (match versions).
      java: corretto8

  build:
    commands:

      # Build the project
      - mvn package

      # move the generated WAR file from the target folder to the current directory and 
      # rename it "quizzard.war"
      - mv target/*.war quizzard.war

  post_build:
    commands:

      # Due to the way that CodePipeline works, we need to extract the contents of the 
      # generated WAR into an extraction directory we will call "artifact".
      - unzip quizzard.war -d artifact/

      # Move the WEB-INF folder from the extraction directory to the current directory
      - mv artifact/WEB-INF WEB-INF

# Declare build artifacts to be passed to the next stage of the pipeline
artifacts:
  files:
    - WEB-INF/**/*
  name: artifact
```

- The default values for the remainder of the options for the pipeline creation are fine and can be left alone.
- Click: `Continue to CodePipeline`
