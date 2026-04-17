CREATE TABLE media (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    media_type VARCHAR(50) NOT NULL,
    release_year INT,
    description TEXT,
    cover_image_url TEXT,
    rating INT,
    status VARCHAR(50),
    progress INT,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);
