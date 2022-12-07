package day7;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdventFile {
    private int size;
    private String name;
    private Directory parentDirectory;
}
