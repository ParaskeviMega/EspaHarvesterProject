CREATE TABLE project_subProject_info
(
    projectId    integer NOT NULL,
    subProjectId integer NOT NULL,
    payment      decimal,
    totalBudget  decimal,
    budget       decimal,
    singleSeller BOOLEAN,
    PRIMARY KEY (projectId, subProjectId)
)