package elastic.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Id;


/**
 * @author Sebastian Hardt (s.hardt@micromata.de)
 * @author Johannes Unterstein(unterstein@me.com)
 */
public class AbstractNode {

  @Id
  public String id;

  @CreatedDate
  public Long createdDate;

  @LastModifiedDate
  public Long lastModifiedDate;

}
