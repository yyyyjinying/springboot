package com.bjsxt.service;

import java.util.List;

public interface FilesService {
    List<com.bjsxt.pojo.Files> show();

    int updCount(int id, String name);
}
