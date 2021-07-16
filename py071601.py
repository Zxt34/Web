import unittest
import py0716

def creatsuite():
    #addTest:
    # suite = unittest.TestSuite()
    # suite.addTest(py0716.Baidu1("test_hao"))
    # suite.addTest(py0716.Baidu1("test_baidusearch"))
    # return suite

    #makesuit:
    # suite = unittest.TestSuite()
    # suite.addTest(unittest.makeSuite(py0716.Baidu1))
    # return suite

    #TestLoader:
    # suite1 = unittest.TestLoader.loadTestsFromTestCase(py0716.Baidu1)
    # suite = unittest.TestSuite([suite1])
    # return suite

    #discover:
    discover = unittest.defaultTestLoader.discover("../",pattern="py*.py",top_level_dir=None)

if __name__ == "__main__":
    suite = creatsuite()
    runner = unittest.TextTestRunner(verbosity=1)
    runner.run(suite)