INSERT INTO trainings (title, description,  duration, active)
SELECT 'Spring Boot', 'Développement d''applications Java avec Spring Boot',  2, true
WHERE NOT EXISTS (
    SELECT 1 FROM trainings WHERE title = 'Spring Boot'
);

INSERT INTO trainings (title, description, duration, active)
SELECT 'Docker', 'Conteneurisation d''applications', 2, true
WHERE NOT EXISTS (
    SELECT 1 FROM trainings WHERE title = 'Docker'
);

INSERT INTO trainings (title, description, duration, active)
SELECT 'CI/CD avec GitLab', 'Automatisation des pipelines d''intégration et de déploiement',  3, true
WHERE NOT EXISTS (
    SELECT 1 FROM trainings WHERE title = 'CI/CD avec GitLab'
);