object Artifacts {
    val model = Artifact(
        group = "${Groups.APP}.${SubGroups.CORE}",
        version = "1.0.8",
        name = "model"
    )

    object Presentation {
        val redux = Artifact(
            group = "${Groups.APP}.${SubGroups.PRESENTATION}",
            version = "1.3.1",
            name = "presentation-redux"
        )

        val reduxCoroutines = Artifact(
            group = "${Groups.APP}.${SubGroups.PRESENTATION}",
            version = "1.3.0",
            name = "presentation-redux-coroutines"
        )
    }
}