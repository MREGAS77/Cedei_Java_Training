package net.atos.cedei.cursospring.dao;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import net.atos.cedei.cursospring.domain.TaskEntity;

@Repository("taskDAO")
public class TaskDAOImpl implements TaskDAO {

    private Map<Long, TaskEntity> initTasks = new TreeMap<Long, TaskEntity>();

    @PostConstruct
    public void initDao() {
        initTasks.put(Long.valueOf(1), new TaskEntity(Long.valueOf(1), "Task 1: Texto1"));
        initTasks.put(Long.valueOf(2), new TaskEntity(Long.valueOf(2), "Task 2: Texto1"));
        initTasks.put(Long.valueOf(3), new TaskEntity(Long.valueOf(3), "Task 3: Texto2"));
        initTasks.put(Long.valueOf(4), new TaskEntity(Long.valueOf(4), "Task 4: Texto2"));
        initTasks.put(Long.valueOf(5), new TaskEntity(Long.valueOf(5), "Task 5: Texto2"));
    }

    @Override
    public List<TaskEntity> retrieveTasks() {
        return new ArrayList<TaskEntity>(initTasks.values());
    }

    @Override
    public List<TaskEntity> findTasksByName(String name) {
        Pattern pattern = Pattern.compile(name);

        Iterable<TaskEntity> filtered = Iterables.filter(initTasks.values(), new ContainsPatternPredicate(pattern));

        List<TaskEntity> resultList = Lists.newArrayList(filtered);
        return resultList;
    }

    private static class ContainsPatternPredicate implements Predicate<TaskEntity>, Serializable {

        private static final long serialVersionUID = 2000156700727103861L;

        private final Pattern pattern;

        private ContainsPatternPredicate(Pattern pattern) {
            this.pattern = checkNotNull(pattern);
        }

        private ContainsPatternPredicate(String patternStr) {
            this(Pattern.compile(patternStr));
        }

        @Override
        public boolean apply(TaskEntity t) {
            return pattern.matcher(t.getName()).find();
        }

        @Override
        public int hashCode() {
            // Pattern uses Object.hashCode, so we have to reach
            // inside to build a hashCode consistent with equals.

            return Objects.hashCode(pattern.pattern(), pattern.flags());
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof ContainsPatternPredicate) {
                ContainsPatternPredicate that = (ContainsPatternPredicate) obj;

                // Pattern uses Object (identity) equality, so we have to reach
                // inside to compare individual fields.
                return Objects.equal(pattern.pattern(), that.pattern.pattern()) && Objects.equal(pattern.flags(), that.pattern.flags());
            }
            return false;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this).add("pattern", pattern).add("pattern.flags", Integer.toHexString(pattern.flags())).toString();
        }
    }
}
