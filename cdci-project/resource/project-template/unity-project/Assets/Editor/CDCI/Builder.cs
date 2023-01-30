using System.Linq;
using UnityEditor;
using UnityEngine;
using UnityEngine.SceneManagement;

public static class Builder
{
    public static bool WebGLBuild() => Build(BuildTarget.WebGL);

    public static bool Build(BuildTarget buildTarget = BuildTarget.NoTarget)
    {
        if (buildTarget == BuildTarget.NoTarget) buildTarget = EditorUserBuildSettings.activeBuildTarget;
        string outputPath = null;
        string[] args = System.Environment.GetCommandLineArgs();
        for (int i = 0; i < args.Length; i++)
        {
            Debug.Log("ARG " + i + ": " + args[i]);
            if (args[i] == "-outputPath")
            {
                outputPath = args[i + 1];
            }
        }
        if (outputPath == null) throw new System.Exception("[-outputPath] option or parameter was not found");
        return BuildPipeline.BuildPlayer(new BuildPlayerOptions
        {
            locationPathName = outputPath,
            target = buildTarget,
            targetGroup = EditorUserBuildSettings.selectedBuildTargetGroup,
            scenes = GetScenes(),
            options = BuildOptions.None,
        });
    }

    private static string[] GetScenes() =>
    Enumerable
    .Range(0, SceneManager.sceneCountInBuildSettings)
    .Select(SceneUtility.GetScenePathByBuildIndex).ToArray();
}
