package co.touchlab.sessionize

import co.touchlab.sessionize.jsondata.SponsorGroup

class SponsorViewModel {
    fun load(proc: (List<SponsorGroup>) -> Unit) {
        SponsorsModel.loadSponsorsFromCache(proc)
    }
}