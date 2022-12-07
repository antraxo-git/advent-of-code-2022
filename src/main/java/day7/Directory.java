package day7;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Directory {
    private String name;
    private Map<String, Directory> nestedDirectories = new HashMap<>();
    private List<AdventFile> files = new ArrayList<>();
    private Directory parentDirectory;

    public int size() {
        return files.stream()
                .map(AdventFile::getSize)
                .reduce(0, Integer::sum) + nestedDirectories.values().stream()
                                                                .map(Directory::size)
                                                                .reduce(0, Integer::sum);
    }
}
