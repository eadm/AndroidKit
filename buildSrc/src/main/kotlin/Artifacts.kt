object Artifacts {
    val model = Artifact(
        group = "${Groups.APP}.${SubGroups.CORE}",
        version = "1.0.7",
        name = "model"
    )

    object Presentation {
        val redux = Artifact(
            group = "${Groups.APP}.${SubGroups.PRESENTATION}",
            version = "1.2.0",
            name = "presentation-redux"
        )
    }
}