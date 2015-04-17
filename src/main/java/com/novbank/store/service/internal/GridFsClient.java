package com.novbank.store.service.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Component;

/**
 * Created by HP on 2015/4/17.
 */
@Component
public class GridFsClient {
    @Autowired
    GridFsOperations operations;
    public void storeFileToGridFs() {

        /*FileMetadata metadata = new FileMetadata();
        // populate metadata
        Resource file = … // lookup File or Resource

        operations.store(file.getInputStream(), "filename.txt", metadata);*/
    }

    public void findFilesInGridFs() {
        //List<GridFSDBFile> result = operations.find(query(whereFilename().is("filename.txt")));
    }

    public void readFilesFromGridFs() {
        //GridFsResources[] txtFiles = operations.getResources("*.txt");
    }
}
