package com.akvamarin.merge_sorting.options;
import com.akvamarin.merge_sorting.options.params_app.DataType;
import com.akvamarin.merge_sorting.options.params_app.TypeSort;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Options {
    List<File> inputFiles;
    DataType dataType;
    TypeSort typeSort;
    File output;

    public Options(){
        this.inputFiles = new ArrayList<>();
        this.dataType = DataType.NOT_INIT;
        this.typeSort = TypeSort.ASC;
        this.output = null;
    }

    public Options(List<File> inputFiles, DataType dataType, TypeSort typeSort, File output){
        this.inputFiles = inputFiles;
        this.dataType = dataType;
        this.typeSort = typeSort;
        this.output = output;
    }

    public void addInputFile(File inputFile) {
        inputFiles.add(inputFile);
    }

    public void setInputFiles(List<File> inputFiles) {
        this.inputFiles = inputFiles;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public void setTypeSort(TypeSort typeSort) {
        this.typeSort = typeSort;
    }

    public void setOutput(File output) {
        this.output = output;
    }

    public List<File> getInputFiles() {
        return inputFiles;
    }

    public DataType getDataType() {
        return dataType;
    }

    public TypeSort getTypeSort() {
        return typeSort;
    }

    public File getOutput() {
        return output;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Options){
            Options options = (Options) obj;
            return Objects.equals(inputFiles, options.inputFiles) &&
                    dataType == options.dataType &&
                    typeSort == options.typeSort &&
                    Objects.equals(output, options.output);
        }
        return  false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(inputFiles, dataType, typeSort, output);
    }

    @Override
    public String toString() {
        return "Options{" +
                "inputFiles=" + inputFiles +
                ", dataType=" + dataType +
                ", typeSort=" + typeSort +
                ", output=" + output +
                '}';
    }
}
