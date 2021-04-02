bash "$PWD/scripts/build.sh" && \
    rm -rf target/googleshoppingxmloccjava.jar && \
    mv target/googleshoppingxmloccjava-jar-with-dependencies.jar target/googleshoppingxmloccjava.jar && \
    java -jar target/googleshoppingxmloccjava.jar;
