package tw.noah.spring.boot.vue.example.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimplePage<T>{

  private List<T> content = new ArrayList<>();

  private int pageSize;

  private int currentPage;

  private int totalPages;

  private long totalRows;
}

