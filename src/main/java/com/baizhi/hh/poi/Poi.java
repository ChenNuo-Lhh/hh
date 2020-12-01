package com.baizhi.hh.poi;

import java.io.File;

public interface Poi {

    void PoiImport(File file, String Sheet);

    void PoiExport(File file, String Sheet);

}
