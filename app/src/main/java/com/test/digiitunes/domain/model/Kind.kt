package com.test.digiitunes.domain.model

enum class Kind(val text: String) {
    COACHED_AUDIO("coached-audio"), PODCAST("podcast"),
    PODCAST_EPISODE("podcast-episode"),
    SONG("song"), ALBUM("album"),

    FEATURE_MOVIE("feature-movie"), MUSIC_VIDEO("music-video"), TV_EPISODE("tv- episode"),
    BOOK("book"), INTERACTIVE_BOOKLET("interactive- booklet"), SOFTWARE_PACKAGE("software-package")
}
