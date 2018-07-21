#!/ur/bin/env bash
#将项目打包成jar包
mvn clean package;
#分别运行各项目的jar
rm -rf out
for file in Exam1  Exam3
do
    echo "项目-$file:"
    java -jar $file/target/*.jar
done

java -cp Exam2/target/Exam2.jar com.hand.FileTransferServer
java -cp Exam2/target/Exam2.jar com.hand.FileTransferClient
