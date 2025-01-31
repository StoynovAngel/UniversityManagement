INSERT INTO Teacher (name) VALUES
('Dr. Johnson'),
('Prof. Williams'),
('Ms. Taylor'),
('Mr. Brown');

INSERT INTO University_Group (name) VALUES
('43'),
('44'),
('45'),
('39');

INSERT INTO Student (username) VALUES
('jane_doe'),
('mark_smith'),
('emma_jones'),
('lucas_white'),
('sophia_black'),
('teodor_marinov'),
('dimitar_stefanov');

INSERT INTO Subject (name, hours_per_week, teacher_id, description) VALUES
('Physics', 4, 2, 'Introduction to Mechanics and Thermodynamics'),
('Chemistry', 3, 3, 'Basic Chemistry with Lab'),
('Biology', 5, 4, 'Human Anatomy and Physiology'),
('Computer Science', 6, 1, 'Programming and Algorithms'),
('Math', 2, 1, 'Calculus two');

INSERT INTO Group_Student (group_id, student_id) VALUES
(1, 1),
(1, 2),
(2, 3),
(2, 4),
(3, 5);

INSERT INTO Subject_Student (subject_id, student_id) VALUES
(1, 1),
(1, 2),
(2, 3),
(3, 4),
(4, 5);

INSERT INTO Grade (name, mark, teacher_id, student_id, date_of_grading, grade_type) VALUES
('Physics', 4.20, 1, 1, '2025-01-02', 'FINAL_EXAM'),
('Chemistry', 5.0, 1, 2, '2025-01-03', 'MID_EXAM'),
('Biology', 3.0, 2, 3, '2025-01-04', 'HOMEWORK'),
('Computer Science', 5.50, 3, 4, '2025-01-05', 'PROJECT_EXAM'),
('Math', 5.75, 4, 5, '2025-01-06', 'ORAL');
