
package membershiporm.dao;

import java.util.List;
import membershiporm.model.JenisMember;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class JenisMemberDao {

    private final SqlSessionFactory sqlSessionFactory;

    public JenisMemberDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public int insert(JenisMember jenisMember) {
        int result;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            result = session.insert("mapper.JenisMemberMapper.insert", jenisMember);
            session.commit();
        }
        return result;
    }
    

    public int update(JenisMember jenisMember){
        int result;
        try(SqlSession session = sqlSessionFactory.openSession()) {
            result = session.update("mapper.JenisMemberMapper.update", jenisMember);
            session.commit();
        }
        return result;
    }

    public int delete(JenisMember jenisMember){
        int result;
        try(SqlSession session = sqlSessionFactory.openSession()) {
            result = session.delete("mapper.JenisMemberMapper.delete", jenisMember);
            session.commit();
        }
        return result;
    }

    public List<JenisMember> findAll() {
        List<JenisMember> result;
        try(SqlSession session = sqlSessionFactory.openSession()) {
            result = session.selectList("mapper.JenisMemberMapper.findAll");
        }
        return result;
    }
}
