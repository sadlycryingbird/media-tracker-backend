CREATE TABLE media (
    id BIGSERIAL PRIMARY KEY,
    media_type VARCHAR(50) NOT NULL,

    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    release_year INT NOT NULL,
    cover_image_url TEXT NOT NULL,

    rating INT CHECK (rating BETWEEN 0 AND 10),
    status VARCHAR(50) CHECK (status IN ('WATCHING', 'COMPLETED', 'DROPPED', 'PLANNED')),
    progress INT CHECK (progress >= 0),

    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);