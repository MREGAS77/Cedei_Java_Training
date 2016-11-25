package net.atos.cedei.cursospring.test.dbsetup;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;

import com.ninja_squad.dbsetup.operation.Operation;

public class CommonOperations {

    //@formatter:off
    public static Operation DELETE_ALL =
        deleteAllFrom(
            "T_TASKS"
        );

    public static Operation INSERT_ALL =
        sequenceOf(
                insertInto("T_TASKS")
                    .columns("ID", "NAME")
                    .values("1", "Task 1: Texto1")
                    .values("2", "Task 2: Texto1")
                    .values("3", "Task 3: Texto2")
                    .values("4", "Task 4: Texto2")
                    .values("5", "Task 5: Texto2")
                    .build()
        );
      //@formatter:on
}
