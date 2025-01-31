CREATE TABLE IF NOT EXISTS Teacher (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS University_Group (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS Student (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    average_grade_overall NUMERIC(5,2) DEFAULT 0.0
);

CREATE TABLE IF NOT EXISTS Subject (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    hours_per_week INT NOT NULL,
    teacher_id INT NOT NULL REFERENCES Teacher(id) ON DELETE CASCADE,
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Group_Student (
    group_id INT NOT NULL REFERENCES University_Group(id) ON DELETE CASCADE,
    student_id INT NOT NULL REFERENCES Student(id) ON DELETE CASCADE,
    PRIMARY KEY (group_id, student_id)
);

CREATE TABLE IF NOT EXISTS Subject_Student (
    subject_id INT NOT NULL REFERENCES Subject(id) ON DELETE CASCADE,
    student_id INT NOT NULL REFERENCES Student(id) ON DELETE CASCADE,
    PRIMARY KEY (subject_id, student_id)
);

CREATE TABLE IF NOT EXISTS Grade (
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    mark DOUBLE PRECISION NOT NULL,
    teacher_id INT NOT NULL REFERENCES Teacher(id) ON DELETE CASCADE,
    student_id INT NOT NULL REFERENCES Student(id) ON DELETE CASCADE,
    date_of_grading DATE NOT NULL DEFAULT CURRENT_DATE,
    grade_type VARCHAR(20) NOT NULL,
    CHECK (grade_type IN ('FINAL_EXAM', 'MID_EXAM', 'ORAL', 'PROJECT_EXAM', 'HOMEWORK'))
);

CREATE RULE update_student_averages_on_insert AS
ON INSERT TO public.grade
DO ALSO (
    UPDATE public.student
    SET average_grade_overall = (
        SELECT COALESCE(AVG(mark), 0.0)
        FROM public.grade
        WHERE student_id = NEW.student_id
    )
    WHERE id = NEW.student_id
);

CREATE RULE update_student_averages_on_update AS
ON UPDATE TO public.grade
DO ALSO (
    UPDATE public.student
    SET average_grade_overall = (
        SELECT COALESCE(AVG(mark), 0.0)
        FROM public.grade
        WHERE student_id = NEW.student_id
    )
    WHERE id = NEW.student_id
);

CREATE RULE update_student_averages_on_delete AS
ON DELETE TO public.grade
DO ALSO (
    UPDATE public.student
    SET average_grade_overall = (
        SELECT COALESCE(AVG(mark), 0.0)
        FROM public.grade
        WHERE student_id = OLD.student_id
    )
    WHERE id = OLD.student_id
);